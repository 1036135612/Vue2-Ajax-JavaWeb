package mysql;

import java.util.List;

import object.lost;

//操作数据库的类
public class UserService {

	private UseServer accontDao = new UseServer();

	public List<lost> findAll() {
		return accontDao.findAllData();
	}
   //测试方法
	public void printAllAccontData() {
		List<lost> allData = accontDao.findAllData();
		if (null == allData || allData.size() == 0) {

			System.out.println("没有查到数据");

		} else {
			for (lost entity : allData) {
				System.out.println(entity.getLname());
				System.out.println(entity.getwraparound());
				System.out.println(entity.getprice());

			}
		}
	}

	public lost findByYhm(String Yname) {

		return accontDao.findDataByYnm(Yname);
	}
}
