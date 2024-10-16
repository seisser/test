import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductionIdataTest {


    @Test
    void getMainWork() {
        ProductionIdata testProductionIdata = new ProductionIdata();
        assertEquals(IIdata.MainWork.Paper,testProductionIdata.getMainWork());
    }

    @Test
    void getDepartment() {
        ProductionIdata testProductionIdata = new ProductionIdata();
        assertEquals("Production",testProductionIdata.getDepartment());
    }
}