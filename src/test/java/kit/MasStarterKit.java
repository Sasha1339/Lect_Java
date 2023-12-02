package kit;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import ru.mpei.lectAgent4.AgentBuyer;

import java.util.Map;
import java.util.Properties;

public class MasStarterKit {

    public static void startPlatform() {
        Map<String, Class<?>> agents = Map.of("buyer", AgentBuyer.class);
        Properties props = new ExtendedProperties();
        props.setProperty("gui", "true");
        props.setProperty("agents", formAgentsCfg(agents));
        ProfileImpl p = new ProfileImpl();

        Runtime.instance().setCloseVM(true);
        Runtime.instance().createMainContainer(p);

    }

    private static String formAgentsCfg(Map<String, Class<?>> createAgents){
        String outString = "";
        for (var entry : createAgents.entrySet()) { //смотрит что справа от равно и сам приравнивает тип
            outString += entry.getKey()+":"+entry.getValue().getName()+";";
        }
        System.out.println(outString);
        return outString;
    }

}
