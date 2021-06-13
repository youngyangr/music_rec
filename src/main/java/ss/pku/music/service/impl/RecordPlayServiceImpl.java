package ss.pku.music.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ss.pku.music.dao.RecordPlayDao;
import ss.pku.music.dao.UserDao;
import ss.pku.music.model.PlayRecord;
import ss.pku.music.model.User;
import ss.pku.music.service.RecordPlayService;
import ss.pku.music.utils.Request;

@Service("recordPlayService")
public class RecordPlayServiceImpl implements RecordPlayService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private RecordPlayDao recordPlayDao;
	

	public void recordPlay(HttpServletRequest request, int songId) {
		User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		recordPlayDao.insert(new PlayRecord(user.getUserId(),songId));
		
	}


	public List<PlayRecord> getAllRecords() {
		return recordPlayDao.selectAll();
	}
	

}
