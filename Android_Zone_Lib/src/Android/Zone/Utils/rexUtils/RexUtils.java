package Android.Zone.Utils.rexUtils;
import java.util.List;
import Android.Zone.Utils.rexUtils.RexWorkHelp.Rex_Phone;
import Android.Zone.Utils.rexUtils.RexWorkHelp.Rex_Phone.PhoneEntity;

public class RexUtils {
	
	public static List<PhoneEntity>  byContextGetPhone(String str){
		return Rex_Phone.byContextGetPhone(str);
	}
}
