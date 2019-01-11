package cn.com.isurpass.iremotemessager.targetdecision;

import cn.com.isurpass.iremotemessager.common.util.IRemoteUtils;
import cn.com.isurpass.iremotemessager.domain.*;
import cn.com.isurpass.iremotemessager.framework.IMessageTargetDecision;
import cn.com.isurpass.iremotemessager.service.*;
import cn.com.isurpass.iremotemessager.vo.EventData;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public abstract class TargetDecisionBase implements IMessageTargetDecision 
{
	private static Log log = LogFactory.getLog(TargetDecisionBase.class);

	@Resource
	protected UserService userservice;
	@Resource
	private GatewayService gatewayservice;
	@Resource
	private ZWaveDeviceService zwavedeviceservice ;
	@Resource
	private ZwaveSubDeviceService zwaveSubDeviceService;
	@Resource
	private InfraredDeviceService infraredDeviceService;
	@Resource
	private CameraService cameraService;
	@Resource
	private SceneService sceneService;
	@Resource
	private RoomService roomService;
	@Resource
	private DeviceGroupService deviceGroupService;
	@Resource
	private PartitionService partitionService;
	@Resource
	private NotificationSettingService notificationSettingService;
	
	protected EventData data;
	protected JSONObject eventparameters;
	protected JSONObject domainobjects = new JSONObject();
	protected User phoneuser ;
	protected Gateway gateway;
	protected ZWaveDevice zwaveDevice ;
	protected InfraredDevice infraredDevice;
	protected Camera camera;
	protected Scene scene;
	protected Room room;
	protected DeviceGroup devicegroup;
	protected ZWaveSubDevice subZwaveDevice;
	protected Partition partition;
	protected NotificationSetting notificationSetting;
	
	@Override
	public List<User> messageTarget(EventData data)
	{
		this.data = data;
		this.eventparameters = data.getEventparameters();
		data.setDomainobjects(domainobjects);
		
		initDomainObject();

		return distinctUserListById(descision());
	}

	protected abstract List<User> descision();

	protected void initDomainObject() {
		if (eventparameters.containsKey("subdeviceid") && eventparameters.getInteger("subdeviceid") != 0) {
			subZwaveDevice = zwaveSubDeviceService.findById(eventparameters.getInteger("subdeviceid"));
			zwaveDevice = subZwaveDevice.getZwavedevice();
			if (zwaveDevice != null) {
				gateway = gatewayservice.findById(zwaveDevice.getDeviceid());
				phoneuser = userservice.findById(gateway.getPhoneuserid());
			}
		}
		if (eventparameters.containsKey("zwavedeviceid") && eventparameters.getInteger("zwavedeviceid") != 0) {
			zwaveDevice = zwavedeviceservice.findById(eventparameters.getInteger("zwavedeviceid"));
			gateway = gatewayservice.findById(zwaveDevice.getDeviceid());
			phoneuser = userservice.findById(gateway.getPhoneuserid());
		}
		if (eventparameters.containsKey("infrareddeviceid") && eventparameters.getInteger("infrareddeviceid") != 0) {
			infraredDevice = infraredDeviceService.findById(eventparameters.getInteger("infrareddeviceid"));
			gateway = gatewayservice.findById(infraredDevice.getDeviceid());
			phoneuser = userservice.findById(gateway.getPhoneuserid());
		}
		if (eventparameters.containsKey("cameraid") && eventparameters.getInteger("cameraid") != 0) {
			camera = cameraService.findById(eventparameters.getInteger("cameraid"));
			gateway = gatewayservice.findById(camera.getDeviceid());
			phoneuser = userservice.findById(gateway.getPhoneuserid());
		}
		if (eventparameters.containsKey("scenedbid") && eventparameters.getInteger("scenedbid") != 0) {
			scene = sceneService.findById(eventparameters.getInteger("scenedbid"));
			phoneuser = userservice.findById(scene.getPhoneuserid());
		}
		if (eventparameters.containsKey("roomdbid") && eventparameters.getInteger("roomdbid") != 0) {
			room = roomService.findById(eventparameters.getInteger("roomdbid"));
			phoneuser = userservice.findById(room.getPhoneuserid());
		}
		if (eventparameters.containsKey("devicegroupid") && eventparameters.getInteger("devicegroupid") != 0) {
			devicegroup = deviceGroupService.findById(eventparameters.getInteger("devicegroupid"));
			phoneuser = userservice.findById(devicegroup.getPhoneuserid());
		}
		if (eventparameters.containsKey("partitionid") && eventparameters.getInteger("partitionid") != 0) {
			partition = partitionService.findById(eventparameters.getInteger("partitionid"));
			if (partition.getZwavedevice() != null) {
				gateway = gatewayservice.findById(partition.getZwavedevice().getDeviceid());
				phoneuser = userservice.findById(gateway.getPhoneuserid());
			}
		}
		if (eventparameters.containsKey("notificationsettingid") && eventparameters.getInteger("notificationsettingid") != 0) {
			notificationSetting = notificationSettingService.findById(eventparameters.getInteger("notificationsettingid"));
			phoneuser = userservice.findById(notificationSetting.getPhoneuserid());
		}
		if (gateway == null && eventparameters.containsKey("deviceid") && StringUtils.isNotBlank(eventparameters.getString("deviceid"))) {
			gateway = gatewayservice.findById(eventparameters.getString("deviceid"));
			phoneuser = userservice.findById(gateway.getPhoneuserid());
		}
		if (eventparameters.containsKey("phoneuserid") && eventparameters.getInteger("phoneuserid") != 0) {
			phoneuser = userservice.findById(eventparameters.getInteger("phoneuserid"));
		}

		domainobjects.put("user", phoneuser);
		domainobjects.put("zwavedevice", zwaveDevice);
		domainobjects.put("subzwavedevice", subZwaveDevice);
		domainobjects.put("infrareddevice", infraredDevice);
		domainobjects.put("camera", camera);
		domainobjects.put("gateway", gateway);
		domainobjects.put("scene", scene);
		domainobjects.put("room", room);
		domainobjects.put("devicegroup", devicegroup);
		domainobjects.put("securitypartition", partition);
		domainobjects.put("notificationsetting", notificationSetting);
		domainobjects.put("partition", partition);

	}

	protected List<User> copyUser(List<User> userList, List<User> ul) {
		if (IRemoteUtils.isBlank(userList)) {
			userList = new ArrayList<>();
		}

		if (IRemoteUtils.isNotBlank(ul)) {
			userList.addAll(ul);
		}

		return userList;
	}

	private List<User> distinctUserListById(List<User> userList){
		HashSet<String> set = new HashSet();
		ArrayList<User> list = new ArrayList<>();

		Iterator<User> iterator = userList.iterator();
		String key;
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getPhoneuserid() == 0 && StringUtils.isBlank(user.getPhonenumber())
					&& StringUtils.isBlank(user.getMail())) {
				continue;
			}
			if (!set.contains(key = distinctKey(user))) {
				list.add(user);
				set.add(key);
			}
		}
		return list;
	}

	private String distinctKey(User user){
		return user.getPhoneuserid() + user.getCountrycode() + user.getPhonenumber()+user.getMail();
	}
}
