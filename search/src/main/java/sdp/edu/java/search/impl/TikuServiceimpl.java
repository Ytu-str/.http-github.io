package sdp.edu.java.search.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sdp.edu.java.search.Dao.TikuDao;
import sdp.edu.java.search.Service.TikuService;
import sdp.edu.java.search.bean.Tiku;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Service("tikuservice")
public class TikuServiceimpl implements TikuService {
	@Autowired
	private TikuDao tikudao;

	@Override
	public List<Tiku> findlist(int limits, int pages) {
		int floats = (pages - 1) * limits;
		return tikudao.findtick(floats, limits);
	}

	@Override
	public int findNum(int limits, int pages) {
		int floats = (pages - 1) * limits;
		return tikudao.findNum(limits, floats);
	}

	@Override
	public int adduser(MultipartFile file) throws IOException {
		int count = 0;
		List<Tiku> list = new ArrayList<>();
		Workbook wb = null;
		String filename = file.getOriginalFilename();
		String suffix = filename.substring(filename.lastIndexOf(".") + 1);
		//把文件转换为输入流
		InputStream inputStream = file.getInputStream();
		//判断是否是xlsx后缀
		if (suffix.equals("xlsx")) {
			wb = new XSSFWorkbook(inputStream);
		} else {
			wb = new HSSFWorkbook(inputStream);
		}
		//获得第一张sheet表
		Sheet sheet = wb.getSheetAt(0);
		if (sheet != null) {
			//数据是从第三行开始，所以这里从第三行开始遍历
			for (int line = 2; line <= sheet.getLastRowNum(); line++) {
				Tiku ti = new Tiku();
				Row row = sheet.getRow(line);
//				if (row == null)
//					continue;
//				//判断单元格类型是否为文本类型
//				if (1 != row.getCell(0).getCellType()) {
//					continue;
//				}
//
				ti.setTi(row.getCell(0).getStringCellValue());
//				if (1 != row.getCell(1).getCellType()) {
//					continue;
//				}
				list.add(ti);
			}
		}

		//用mybatis把得到的数据集合插入了数据库
		for (Tiku ti : list) {
			System.out.println(ti);
			int i = tikudao.insertSelective(ti);
			count = count + i;
		}
		list.clear();
		//返回的是插入了多少行
		return count;

	}
}



