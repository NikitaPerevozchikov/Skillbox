import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Purchaselist")
@Getter
@Setter
public class Purchaselist {

    @EmbeddedId
    private Purchaselist.Key id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Getter
    @Setter
    public static class Key implements Serializable {

        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;

        public Key() {
        }

        public Key(String studentName, String courseName) {
            this.studentName = studentName;
            this.courseName = courseName;
        }
    }
}
