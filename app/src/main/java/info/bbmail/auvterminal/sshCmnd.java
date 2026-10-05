package info.bbmail.auvterminal;

/*
 * Auv Common (SSH コマンド発行) Class
 * 2024.04.17 新規作成
*/
import com.jcraft.jsch.*;

/////////////////////////////////////////////////////////////////////////////////////
// Functions
/////////////////////////////////////////////////////////////////////////////////////
public class sshCmnd
{
	public String host = "";
    public String username = "";
    public String password = "";

    public void Setup( String Host, String User, String Pass ){
        host = Host;
        username = User;
        password = Pass;
    }

	public void ssh_shutdown() {
		String commandArgs = "sudo shutdown -h now";
		sshcommand( commandArgs );
	}

	public void ssh_reboot() {
		String commandArgs = "sudo reboot";
		sshcommand( commandArgs );
	}

    public void sshcommand(String commandArgs)
    {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, 22);
            session.setPassword( password );
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Execute the shutdown command
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand( commandArgs );
            channel.connect();

            // Wait for the command to complete
            while (!channel.isClosed()) {
                Thread.sleep(1000);
            }
            channel.disconnect();
            session.disconnect();
        } catch (JSchException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
