// Joseph (Joey) Lincoln || November, 2024 (Sophomore)
// 20 Questions Binary Tree Game in Core Java 21+ LTS
// Revision of Project for Professional GitHub Repo

/*

 */

public class LearningObject {
    private String answer;
    private String topic;
    private String question;
    private String yesOrNo;

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getYesOrNo() {
        return yesOrNo;
    }

    public void setYesOrNo(String yesOrNo) {
        this.yesOrNo = yesOrNo;
    }
    @Override
    public String toString() {
        return "LearningObject [answer=" + answer + ", topic=" + topic + ", question=" + question + ", yesOrNo=" + yesOrNo
                + "]";
    }

}