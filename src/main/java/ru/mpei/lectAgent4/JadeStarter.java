package ru.mpei.lectAgent4;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;

import java.util.Map;
import java.util.Properties;

public class JadeStarter {

    /**
     * вторая часть лекции, запуск автозапускаемых агентов
     *
     * юнит тестирвоание - тестирование одной функции - например вызов поведения
     *
     * что мы получили можно оценивать с помощью метод onEnd,
     *
     * 1. нужна плтаформа, на которой нужно запустить поведения отдельнот агента - startPlatforma
     * 2. сделаем специального тест агента - который будет получать поведение и запускать его
     * 3. нужны сами поведения
     * 4. нужны тестовые параметры - который скажет как оно должно работать
     * getArgs[0] передаем аргументы
     *
     *
     * сценарии для тестирвования
     * 1. запуск платформы
     * 2. задать сами поведения (экземпляры)
     * 3. addAgent - (передаем имя и поведение)
     * 4. подождали нужное количестов времени (заснули поток)
     * 5. вызвали ассерт и проверили
     */

    public static void main(String[] args) {
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
