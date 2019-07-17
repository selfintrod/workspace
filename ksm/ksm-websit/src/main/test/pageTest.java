import com.lingan.ksm.entity.ksmUser;
import com.lingan.ksm.service.UserService;
import com.lingan.ksm.serviceimpl.UserImpl;

public class pageTest {

	
	
	
	public static void main(String[] args)
	{
		
		UserService us=new UserImpl();
		
		String message="";
		ksmUser user;
		user=us.getUserByName("gaohan");
		if(user!=null)	
		message="youciren";
		else message="null";
		//Page<ksmUser> page=new Page<>(1,1);
		//IPage<ksmUser> ipage=us.getUsers(page);
	System.out.println(message);
	}
}
