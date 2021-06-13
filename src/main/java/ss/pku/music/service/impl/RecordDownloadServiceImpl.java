package ss.pku.music.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ss.pku.music.dao.RecordDownloadDao;
import ss.pku.music.dao.UserDao;
import ss.pku.music.model.DownloadRecord;
import ss.pku.music.model.User;
import ss.pku.music.service.RecordDownloadService;
import ss.pku.music.utils.Request;

@Service("recordDownloadService")
public class RecordDownloadServiceImpl implements RecordDownloadService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private RecordDownloadDao recordDownloadDao;

	public void recordDownload(HttpServletRequest request, int songId) {
		User user=Request.getUserFromHttpServletRequest(request);
		if(user==null) {
			return;
		}
		//记录当前用户的下载记录
		user=userDao.selectByUser(user);
		recordDownloadDao.insert(new DownloadRecord(user.getUserId(),songId));
	}

	public List<DownloadRecord> getAllRecords() {
		return recordDownloadDao.selectAll();
	}

}
