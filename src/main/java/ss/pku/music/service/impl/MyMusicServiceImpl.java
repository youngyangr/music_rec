package ss.pku.music.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ss.pku.music.dao.MyMusicDao;
import ss.pku.music.dao.TrendingRecDao;
import ss.pku.music.dao.UserDao;
import ss.pku.music.model.Collection;
import ss.pku.music.model.Song;
import ss.pku.music.model.User;
import ss.pku.music.service.MyMusicService;
import ss.pku.music.utils.Request;

@Service("myMusicService")
public class MyMusicServiceImpl implements MyMusicService {
	@Autowired
	private MyMusicDao myMusicDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TrendingRecDao trendingRecDao;

	
	public List<Song> getMyCollectionWithCollectionFlag(HttpServletRequest request) {
		List<Song> myCollectionList = new ArrayList<Song>();
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		myCollectionList = myMusicDao.selectCollectedSong(user);
		//为该用户收藏的歌曲加上标记
		if(myCollectionList!=null) {
			for(Song t:myCollectionList) {
				t.setWhetherCollected(true);
			}
		}
		return myCollectionList;
	}

	public List<Song> getMyRecentPlayListWithCollectionFlag(HttpServletRequest request) {
		List<Song> myRecentPalyList = new ArrayList<Song>();
		List<Collection> collectionList=new ArrayList<Collection>();
		User user = userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		myRecentPalyList = myMusicDao.selectMyRecentSong(user);
		collectionList=trendingRecDao.getCollection(user);
		//在新碟上架列表中给已经被该用户收藏的歌曲加上标记
		if(collectionList!=null && myRecentPalyList!=null) {
			for(Collection c:collectionList) {
				for(Song t:myRecentPalyList) {
					if(c.getSongId()==t.getSongId()) {
						t.setWhetherCollected(true);
					}
				}
			}
		}
		return myRecentPalyList;
	}

}
