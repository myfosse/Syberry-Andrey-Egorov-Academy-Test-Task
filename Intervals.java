import java.io.Serializable;
import java.util.*;

public class Intervals {

    private final static Map<String, Pair<Integer, Integer>> MAP_OF_INTERVALS = new HashMap<>();
    private final static List<Pair<String, Integer>> LIST_OF_NOTE_DIFFERENCE = Arrays.asList(
            new Pair<>("C", 2),
            new Pair<>("D", 2),
            new Pair<>("E", 1),
            new Pair<>("F", 2),
            new Pair<>("G", 2),
            new Pair<>("A", 2),
            new Pair<>("B", 1),
            new Pair<>("C", 2),
            new Pair<>("D", 2),
            new Pair<>("E", 1),
            new Pair<>("F", 2),
            new Pair<>("G", 2),
            new Pair<>("A", 2),
            new Pair<>("B", 1)
    );

    //private final static String ASC = "asc";
    private final static String DSC = "dsc";
    private final static String FLAT = "b";
    private final static String SHARP = "#";

    static {
        MAP_OF_INTERVALS.put("m2", new Pair<>(1, 2));
        MAP_OF_INTERVALS.put("M2", new Pair<>(2, 2));
        MAP_OF_INTERVALS.put("m3", new Pair<>(3, 3));
        MAP_OF_INTERVALS.put("M3", new Pair<>(4, 3));
        MAP_OF_INTERVALS.put("P4", new Pair<>(5, 4));
        MAP_OF_INTERVALS.put("P5", new Pair<>(7, 5));
        MAP_OF_INTERVALS.put("m6", new Pair<>(8, 6));
        MAP_OF_INTERVALS.put("M6", new Pair<>(9, 6));
        MAP_OF_INTERVALS.put("m7", new Pair<>(10, 7));
        MAP_OF_INTERVALS.put("M7", new Pair<>(11, 7));
        MAP_OF_INTERVALS.put("P8", new Pair<>(12, 8));
    }

    public static String intervalConstruction(String[] args) throws IllegalArgumentException {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Illegal number of elements in input array");
        }
        if (args[0].equals("P8")) {
            return args[1];
        }

        StringBuilder stringBuilderAnswer = new StringBuilder();
        String startNote = args[1].substring(0, 1);
        int amountOfSemitones = 0;
        int i;
        int j;

        if (args.length == 3 && args[2].equals(DSC)) {
            if (args[1].contains(SHARP)) {
                amountOfSemitones++;
            } else if (args[1].contains(FLAT)) {
                amountOfSemitones--;
            }

            i = 13;
            while (!startNote.equals(LIST_OF_NOTE_DIFFERENCE.get(i).getKey())) {
                i--;
            }
            i--;

            for (j = i; j > i - MAP_OF_INTERVALS.get(args[0]).getValue() + 1; j--) {
                amountOfSemitones += LIST_OF_NOTE_DIFFERENCE.get(j).getValue();
            }
            j++;

            stringBuilderAnswer.append(LIST_OF_NOTE_DIFFERENCE.get(j).getKey());
            while (amountOfSemitones != MAP_OF_INTERVALS.get(args[0]).getKey()) {
                if (amountOfSemitones - MAP_OF_INTERVALS.get(args[0]).getKey() < 0) {
                    stringBuilderAnswer.append(FLAT);
                    amountOfSemitones++;
                } else {
                    stringBuilderAnswer.append(SHARP);
                    amountOfSemitones--;
                }
            }
        } else {
            if (args[1].contains(SHARP)) {
                amountOfSemitones--;
            } else if (args[1].contains(FLAT)) {
                amountOfSemitones++;
            }

            i = 0;
            while (!startNote.equals(LIST_OF_NOTE_DIFFERENCE.get(i).getKey())) {
                i++;
            }

            for (j = i; j < MAP_OF_INTERVALS.get(args[0]).getValue() + i - 1; j++) {
                amountOfSemitones += LIST_OF_NOTE_DIFFERENCE.get(j).getValue();
            }

            stringBuilderAnswer.append(LIST_OF_NOTE_DIFFERENCE.get(j).getKey());
            while (amountOfSemitones != MAP_OF_INTERVALS.get(args[0]).getKey()) {
                if (amountOfSemitones - MAP_OF_INTERVALS.get(args[0]).getKey() < 0) {
                    stringBuilderAnswer.append(SHARP);
                    amountOfSemitones++;
                } else {
                    stringBuilderAnswer.append(FLAT);
                    amountOfSemitones--;
                }
            }
        }

        return stringBuilderAnswer.toString();
    }

    public static String intervalIdentification(String[] args) throws IllegalArgumentException {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Illegal number of elements in input array");
        }
        if ((args[0].length() > 1 || args[1].length() > 1) & args[0].substring(0, 1).equals(args[1].substring(0, 1))) {
            throw new IllegalArgumentException("Cannot identify the interval");
        }
        if (args[0].equals(args[1])) {
            return "P8";
        }

        String startNote = args[0];
        String endNote = args[1];
        int amountOfSemitones = 0;
        int amountOfNotes = 0;
        int i;
        int j;

        if (args.length == 3 && args[2].equals(DSC)) {
            while (startNote.length() != 1 | endNote.length() != 1) {
                if (startNote.contains(SHARP)) {
                    startNote = startNote.replaceFirst(SHARP, "");
                    amountOfSemitones++;
                } else if (startNote.contains(FLAT)) {
                    startNote = startNote.replaceFirst(FLAT, "");
                    amountOfSemitones--;
                } else if (endNote.contains(SHARP)) {
                    endNote = endNote.replaceFirst(SHARP, "");
                    amountOfSemitones--;
                } else if (endNote.contains(FLAT)) {
                    endNote = endNote.replaceFirst(FLAT, "");
                    amountOfSemitones++;
                }
            }

            i = 13;
            while (!startNote.equals(LIST_OF_NOTE_DIFFERENCE.get(i).getKey())) {
                i--;
            }
            j = i - 1;
            while (!endNote.equals(LIST_OF_NOTE_DIFFERENCE.get(j).getKey())) {
                j--;
            }
            for (; j < i; j++) {
                amountOfSemitones += LIST_OF_NOTE_DIFFERENCE.get(j).getValue();
                amountOfNotes++;
            }
        }else {
            while (startNote.length() != 1 | endNote.length() != 1) {
                if (startNote.contains(SHARP)) {
                    startNote = startNote.replaceFirst(SHARP, "");
                    amountOfSemitones--;
                } else if (startNote.contains(FLAT)) {
                    startNote = startNote.replaceFirst(FLAT, "");
                    amountOfSemitones++;
                } else if (endNote.contains(SHARP)) {
                    endNote = endNote.replaceFirst(SHARP, "");
                    amountOfSemitones++;
                } else if (endNote.contains(FLAT)) {
                    endNote = endNote.replaceFirst(FLAT, "");
                    amountOfSemitones--;
                }
            }

            i = 0;
            while (!startNote.equals(LIST_OF_NOTE_DIFFERENCE.get(i).getKey())) {
                i++;
            }
            j = i + 1;
            while (!endNote.equals(LIST_OF_NOTE_DIFFERENCE.get(j).getKey())) {
                j++;
            }

            for (; i < j; i++) {
                amountOfSemitones += LIST_OF_NOTE_DIFFERENCE.get(i).getValue();
                amountOfNotes++;
            }
        }
        amountOfNotes++;

        Pair<Integer, Integer> value = new Pair<>(amountOfSemitones, amountOfNotes);
        Optional<String> result = MAP_OF_INTERVALS.entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new IllegalArgumentException("Cannot identify the interval");
        }
    }

}

class Pair<T, V> implements Serializable {
    T key;
    V value;

    public Pair(T key, V value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

    @Override
    public int hashCode() {
        return key.hashCode() * 13 + (value == null ? 0 : value.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
            if (value != null ? !value.equals(pair.value) : pair.value != null) return false;
            return true;
        }
        return false;
    }
}