package tugofwar.mechanic;

public class TugOfWarField implements ITugOfWarMechanic {

    private int position = 0;   // -10 ... 0 ... +10
    private final int minPosition = -10;
    private final int maxPosition = 10;

   @Override
public void onCorrectAnswer() {
    // Kalau bener mendekat ke pemain (kiri)
    if (position > minPosition) {
        position--;
    }
}

@Override
public void onWrongAnswer() {
    // Kalau salah mendekat ke CPU (kanan)
    if (position < maxPosition) {
        position++;
    }
}


    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String renderField() {
        int length = maxPosition - minPosition + 1;
        char[] field = new char[length];
        for (int i = 0; i < length; i++) {
            field[i] = '-';
        }
        int index = position - minPosition;
        field[index] = '0';

        return "SISWA  [" + new String(field) + "] CPU";
    }
}
