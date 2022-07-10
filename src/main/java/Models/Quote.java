package Models;

public class Quote {
    private int id;
    private String user_name;
    private String q_text;
    private String teacher_name;
    private String subject_name;
    private String date_of_said;

    public Quote(
                 String user_name,
                 String q_text,
                 String teacher_name,
                 String subject_name,
                 String date_of_said
    ) {
        this.user_name = user_name;
        this.q_text = q_text;
        this.teacher_name = teacher_name;
        this.subject_name = subject_name;
        this.date_of_said = date_of_said;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getQ_text() {
        return q_text;
    }

    public void setQ_text(String q_text) {
        this.q_text = q_text;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", q_text='" + q_text + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", subject_name='" + subject_name + '\'' +
                ", date_of_said='" + date_of_said + '\'' +
                '}';
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getDate_of_said() {
        return date_of_said;
    }

    public void setDate_of_said(String date_of_said) {
        this.date_of_said = date_of_said;
    }

}
