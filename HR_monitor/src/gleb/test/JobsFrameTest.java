import gleb.client.JobsFrame;
import gleb.server.dao.impl.Factory;
import gleb.server.model.Job;
import org.junit.Before;
import org.junit.Test;
import utils.TestUtils;

import javax.swing.*;

import java.util.Random;

import static org.junit.Assert.*;

public class JobsFrameTest {
    JobsFrame frame = new JobsFrame();
    JButton button_ADD_ROW = (JButton) TestUtils.getChildNamed(frame, "button_ADD_ROW");
    JButton button_DELETE_ROW = (JButton) TestUtils.getChildNamed(frame, "button_DELETE_ROW");
    JButton button_DISCARD_CHANGES = (JButton) TestUtils.getChildNamed(frame, "button_DISCARD_CHANGES");
    JButton button_UPDATE_DATABASE = (JButton) TestUtils.getChildNamed(frame, "button_UPDATE_DATABASE");
    JTable jobs_table = (JTable) TestUtils.getChildNamed(frame, "jobs_table");

    @Before
    public void setUp() throws Exception {
        assertNotNull(frame);
        JobsFrame.main(null);

        assertNotNull(button_ADD_ROW);
        assertNotNull(button_DISCARD_CHANGES);
        assertNotNull(button_UPDATE_DATABASE);
        assertNotNull(jobs_table);
    }

    @Test
    public void testAddButton() throws Exception {
        final int rowsAtStart = jobs_table.getRowCount();
        final int addedRows = new Random().nextInt(10) + 1;
        //check row is added after "add" button clicked
        for (int i = 0; i < addedRows; i++)
            button_ADD_ROW.doClick();
        assertEquals(rowsAtStart + addedRows, jobs_table.getRowCount());

        button_DISCARD_CHANGES.doClick();
        assertEquals(rowsAtStart, jobs_table.getRowCount());
    }
    @Test
    public void testDeleteButton() throws Exception {
        final int rowsAtStart = jobs_table.getRowCount();
        final int rowToDelete = new Random().nextInt(rowsAtStart);
        //select random row and delete
        long deletedId = (Long) jobs_table.getValueAt(rowToDelete, 0);
        jobs_table.addRowSelectionInterval(rowToDelete, rowToDelete);
        button_DELETE_ROW.doClick();
        //check the count
        int curRows = rowsAtStart == 0 ? 0 : rowsAtStart - 1;
        assertEquals(curRows, jobs_table.getRowCount());
        //check deleted the correct row
        button_UPDATE_DATABASE.doClick();
        assertEquals(curRows, jobs_table.getRowCount());
        for (Job job : Factory.getJobImpl().getAllJobs()) {
            System.out.println(job.getId() + "-" + deletedId);
            assertNotEquals(job.getId(), deletedId);
        }
        //do not allow rows end
        button_ADD_ROW.doClick();
        button_UPDATE_DATABASE.doClick();
    }
    @Test
    public void testUpdateButton() throws Exception {
    }
    @Test
    public void testDiscardButton() throws Exception {
    }
}
