/**
 * Created by gleb on 24.03.16.
 */
public enum FatalErrs {
    PROBLEM_ARCH_READING(String.format("Cant read archive, expect %s type", IziPatch.ARCH_TYPE)),
    NO_FILE("No such file:"),
    HOME_UNSET("HOME environment vriable is not set");

    private final String desc;

    FatalErrs(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}