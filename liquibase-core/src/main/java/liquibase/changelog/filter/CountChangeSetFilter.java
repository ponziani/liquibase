package liquibase.changelog.filter;

import liquibase.changelog.ChangeSet;

public class CountChangeSetFilter implements ChangeSetFilter {

    private int changeSetsToAllow;
    private int changeSetsSeen;

    public CountChangeSetFilter(int changeSetsToAllow) {
        this.changeSetsToAllow = changeSetsToAllow;
    }

    @Override
    public ChangeSetFilterResult accepts(ChangeSet changeSet) {
        changeSetsSeen++;

        if (changeSetsSeen <= changeSetsToAllow) {
            return new ChangeSetFilterResult(true, "One of "+changeSetsToAllow+" changesets to run", this.getClass(), getMdcName());
        } else {
            String plurality = "changesets";
            if (changeSetsToAllow == 1) {
                plurality = "changeset";
            }
            return new ChangeSetFilterResult(false, "Only running "+changeSetsToAllow+" " + plurality, this.getClass(), getMdcName());
        }
    }

    @Override
    public String getMdcName() {
        return "afterCount";
    }
}
