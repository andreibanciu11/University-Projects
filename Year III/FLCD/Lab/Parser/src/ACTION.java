public enum ACTION {
    SHIFT(1),
    ACCEPT(2),
    REDUCE(3),
    REDUCE_REDUCE_CONFLICT(4),
    SHIFT_REDUCE_CONFLICT(5);

    private final int value;

    ACTION(int value) {
        this.value = value;
    }

    // Optionally, add a method to get the value if needed
    public int getValue() {
        return this.value;
    }
}
