import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DevIdataTest {

    @Test
    void getMainWork() {
        DevIdata testDevIdata = new DevIdata();
        assertEquals(IIdata.MainWork.Digital,testDevIdata.getMainWork());
    }

    @Test
    void getDepartment() {
        DevIdata testDevIdata = new DevIdata();
        assertEquals("Development",testDevIdata.getDepartment());
    }
}