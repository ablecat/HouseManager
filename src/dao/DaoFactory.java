package dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DaoFactory {

	//类似于工厂+单例模式
	private static DaoFactory factory = new DaoFactory();

	private Map<String, Object> map = new ConcurrentHashMap<>();
	
	private DaoFactory() {

	}

	public static DaoFactory getInstance() {
		return factory;
	}

	public Owner_usersDao getOwner_usersDao() {
		Owner_usersDao dao = (Owner_usersDao) map.get("Owner_usersDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new Owner_usersDao();
			map.put("Owner_usersDao", dao);
		}
		return dao;
	}

	public Tenant_usersDao getTenant_usersDao() {
		Tenant_usersDao dao = (Tenant_usersDao) map.get("Tenant_usersDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new Tenant_usersDao();
			map.put("Tenant_usersDao", dao);
		}
		return dao;
	}

	public HouseDao getHouseDao() {
		HouseDao dao = (HouseDao) map.get("HouseDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new HouseDao();
			map.put("HouseDao", dao);
		}
		return dao;
	}

    public VacantHouseDao getVacantHouseDao() {
		VacantHouseDao dao = (VacantHouseDao) map.get("VacantHouseDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new VacantHouseDao();
			map.put("VacantHouseDao", dao);
		}
		return dao;
    }
	public ChargeDao getChargeDao() {
		ChargeDao dao = (ChargeDao) map.get("ChargeDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new ChargeDao();
			map.put("ChargeDao", dao);
		}
		return dao;
	}

	public RecordDao getRecordDao() {
		RecordDao dao = (RecordDao) map.get("RecordDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new RecordDao();
			map.put("RecordDao", dao);
		}
		return dao;
	}

	public RentedHouseDao getRentedHouseDao () {
		RentedHouseDao dao = (RentedHouseDao) map.get("RentedHouseDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new RentedHouseDao();
			map.put("RentedHouseDao", dao);
		}
		return dao;
	}

    public SeenHouseDao getSeenHouseDao() {
		SeenHouseDao dao = (SeenHouseDao) map.get("SeenHouseDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new SeenHouseDao();
			map.put("SeenHouseDao", dao);
		}
		return dao;
    }

	public MessageDao getMessageDao() {
		MessageDao dao = (MessageDao) map.get("MessageDao");
		if(dao != null) {
			return dao;
		}else {
			dao = new MessageDao();
			map.put("MessageDao", dao);
		}
		return dao;
	}
}
