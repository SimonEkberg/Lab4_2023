package service.person;

import org.junit.Test;
import service.CommandService;
import service.ServiceRunner;

import static org.mockito.Mockito.*;

public class ControllServiceTest {

    @Test
    public void testExecuteCommand() {
        CommandService commandService = mock(CommandService.class);
        ServiceRunner controllService = new ServiceRunner();
        controllService.setCommand(commandService);
        controllService.executeService();
        verify(commandService).execute();
    }
}
