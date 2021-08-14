import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
@Getter
@Setter
public class Subscription {
    @EmbeddedId
    private Subscription.Key id;

    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Getter
    @Setter
    public static class Key implements Serializable {

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;

        public Key() {
        }

        public Key(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }
    }
}
