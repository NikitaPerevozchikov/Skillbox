import modelPerson.Person;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class DatingSite {
    private List<Person> peopleList;
    private RedissonClient redissonClient;
    private RKeys rKeys;
    public RScoredSortedSet<String> personSite;
    private AtomicInteger numberIteration;
    private String address;
    private Integer port;

    public DatingSite(String address, Integer port) {
        this.address = address;
        this.port = port;
        peopleList = new ArrayList<>();
        numberIteration = new AtomicInteger(1);
    }

    public void siteEmulation() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + address + ":" + port);
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Не удалось подключиться к Redis");
            System.out.println(Exc.getMessage());
        }
        rKeys = redissonClient.getKeys();
        personSite = redissonClient.getScoredSortedSet("PERSON_SITE");
    }

    public void siteWork() {
        createListPeopleList(peopleList);
        peopleList.forEach(e -> personSite.add(e.getDateRegistration(), e.toString()));
        while (true) {
            if (numberIteration.intValue() % 10 == 0) {
                System.out.println("VIP пользователь с ID: " + showVipPeople());
            }
            if (numberIteration.intValue() % 10 != 0) {
                System.out.println("Очередь пользователя с ID: " + showFirstPeople());
            }
            numberIteration.incrementAndGet();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createListPeopleList(List<Person> peopleList) {
        for (int i = 1; i <= 20; i++) {
            peopleList.add(new Person(i));
        }
    }

    public void addUserInRedis(Long dateVisit, String userId) {
        personSite.add(dateVisit, userId);
    }

    public Double getDateVisitFromRedis(String userId) {
        return personSite.getScore(userId);
    }

    public String showFirstPeople() {
        String firstPeople = personSite.first();
        addUserInRedis(System.currentTimeMillis(), firstPeople);
        return firstPeople;
    }

    public String showVipPeople() {
        List<String> peopleId = new ArrayList<>(personSite.readAll());
        String vipIdPeople = peopleId.get(new Random().nextInt(peopleId.size()));
        addUserInRedis(System.currentTimeMillis(), vipIdPeople);
        return vipIdPeople;
    }
}
