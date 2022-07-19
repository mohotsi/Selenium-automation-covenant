package ae.proctected.covenant.Seleniumautomation.util;

import com.jcraft.jsch.JSchException;
import com.pastdev.jsch.DefaultSessionFactory;
import com.pastdev.jsch.command.CommandRunner;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.sql.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Configuration
public class CommunicationProtocol {

    @Value("${host.machine.api}")
    private String api;

    @Value("${host.communication.port}")
    private Integer port;


    @Value("${host.username}")
    private String username;

    @Value("${host.password}")
    private String password;


    public  String powershellSSH(String command) throws JSchException, IOException {
        DefaultSessionFactory defaultSessionFactory= new DefaultSessionFactory(username,api,port);
        defaultSessionFactory.setPassword(password);
        Map props= new HashMap<String,String>();
        props.put("StrictHostKeyChecking","no");
        props.put("ServerAliveInterval","10");
        defaultSessionFactory.setConfig(props);
        CommandRunner runner= new CommandRunner(defaultSessionFactory);

      val results=runner.execute(command).getStderr();
    runner.close();
    return results;
    }
    public  void powershellSSH3(String command) throws JSchException, IOException, InterruptedException {
        val queue=new ArrayBlockingQueue<>(2);
        final Runnable producer=()->{
            try {
                queue.put(powershellSSH(command));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (JSchException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        new Thread(producer).start();



    }
    public  void powershellSSH2(String command) throws JSchException, IOException {
        val service= Executors.newFixedThreadPool(10);
        Task task=new Task();
        task.setCommand(command);
        task.setUsername(username);
        task.setPort(port);
        task.setApi(api);
        task.setPassword(password);

        Future<String> future= service.submit(task);
        try{
            String results=future.get(1L, TimeUnit.MINUTES);

        }
        catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        } catch (TimeoutException e) {

        }

    }
    static class Task implements Callable<String> {
        private String command;
        private String username;
        private String password;
        private Integer port;
        private String api;
        @Override
        public String call() throws Exception {
            DefaultSessionFactory defaultSessionFactory= new DefaultSessionFactory(username,api,port);
            defaultSessionFactory.setPassword(password);
            Map props= new HashMap<String,String>();
            props.put("StrictHostKeyChecking","no");
            props.put("ServerAliveInterval","10");
            defaultSessionFactory.setConfig(props);
            CommandRunner runner= new CommandRunner(defaultSessionFactory);

            runner.execute(command);
            runner.close();
            return "complete";
        }

        public void setCommand(String command){
            this.command=command;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public void setApi(String api) {
            this.api = api;
        }
    }
}
