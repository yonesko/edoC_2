//import gleb.client.JobsFrame;
//import gleb.server.dao.impl.Factory;
//import org.junit.Test;
//
//import javax.swing.*;
//import java.awt.*;
//
//import static org.junit.Assert.*;
//
//public class JobsFrameTest {
//    JobsFrame frame = new JobsFrame();
//    @Test
//    public void testActionPerformed() throws Exception {
//        assertNotNull(frame);
//        JobsFrame.main(null);
//
//        JButton button_ADD_ROW = (JButton) TestUtils.getChildNamed(frame, "button_ADD_ROW");
//        JButton button_DISCARD_CHANGES = (JButton) TestUtils.getChildNamed(frame, "button_DISCARD_CHANGES");
//        JButton button_UPDATE_DATABASE = (JButton) TestUtils.getChildNamed(frame, "button_UPDATE_DATABASE");
//        JTable jobs_table = (JTable) TestUtils.getChildNamed(frame, "jobs_table");
//
//        assertNotNull(button_ADD_ROW);
//        assertNotNull(button_DISCARD_CHANGES);
//        assertNotNull(button_UPDATE_DATABASE);
//        assertNotNull(jobs_table);
//
//        int rowsAtStart = jobs_table.getRowCount();
//
//        //check row is added after add button clicked
//        button_ADD_ROW.doClick();
//        button_ADD_ROW.doClick();
//        button_ADD_ROW.doClick();
//        assertEquals(rowsAtStart + 3, jobs_table.getRowCount());
//
//        //check table have the same size after discard
//        button_DISCARD_CHANGES.doClick();
//        assertEquals(rowsAtStart, jobs_table.getRowCount());
//
//        //check rows are persisted in DataBase
//        button_ADD_ROW.doClick();
//        button_ADD_ROW.doClick();
//        button_ADD_ROW.doClick();
//        button_UPDATE_DATABASE.doClick();
//        assertEquals(jobs_table.getRowCount(), Factory.getJobImpl().getAllJobs().size());
//
//
//    }
//}
//
//class TestUtils {
//    public static Component getChildNamed(Component parent, String name) {
//
//        // Debug line
//        //System.out.println("Class: " + parent.getClass() +
//        //    " Name: " + parent.getName());
//
//        if (name.equals(parent.getName())) {
//            return parent;
//        }
//
//        if (parent instanceof Container) {
//            Component[] children = ((Container) parent).getComponents();
//
//            for (Component aChildren : children) {
//                Component child = getChildNamed(aChildren, name);
//                if (child != null) {
//                    return child;
//                }
//            }
//        }
//
//        return null;
//    }
//}