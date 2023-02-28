package service.person;

import org.junit.Test;
import service.ServiceCommand;
import service.ServiceRunner;

import static org.mockito.Mockito.*;

public class ControllServiceTest {

    @Test
    public void testExecuteCommand() {
        ServiceCommand commandService = mock(ServiceCommand.class);
        ServiceRunner controllService = new ServiceRunner(commandService);
        //controllService.setCommand(commandService);
        controllService.execute();
        verify(commandService).execute();
    }
}
