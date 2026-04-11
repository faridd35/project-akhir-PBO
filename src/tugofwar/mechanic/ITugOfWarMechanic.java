package tugofwar.mechanic;

public interface ITugOfWarMechanic {

    void onCorrectAnswer();

    void onWrongAnswer();

    int getPosition();

    String renderField();
}
