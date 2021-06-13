package ss.pku.music.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ss.pku.music.dao.SearchDao;
import ss.pku.music.dao.TrendingRecDao;
import ss.pku.music.dao.UserDao;
import ss.pku.music.model.Collection;
import ss.pku.music.model.Review;
import ss.pku.music.model.Song;
import ss.pku.music.model.User;
import ss.pku.music.service.SearchService;
import ss.pku.music.utils.Request;

@Service("searchService")
public class SearchServiceImpl implements SearchService{
	@Autowired
	private SearchDao searchDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TrendingRecDao trendingRecDao;
	

	public List<Song> getSearchSongWithCollectionFlag(HttpServletRequest request, String keyword) {
		List<Song> searchSongList=new ArrayList<Song>();
		List<Collection> collectionList=new ArrayList<Collection>();
		User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		collectionList=trendingRecDao.getCollection(user);
		searchSongList=searchDao.selectSongLikeKeyword(keyword);
		
		//在搜索结果列表中给已经被该用户收藏的歌曲加上标记
		if(collectionList!=null && searchSongList!=null) {
			for(Collection c:collectionList) {
				for(Song t:searchSongList) {
					if(c.getSongId()==t.getSongId()) {
						t.setWhetherCollected(true);
					}
				}
			}
		}
		return searchSongList;
	}


	public List<Review> getSearchReview(String keyword) {
		List<Review> searchReviewList=new ArrayList<Review>();
		searchReviewList=searchDao.selectReviewLikeKeyword(keyword);
		return searchReviewList;
	}


	public List<User> getSearchUser(HttpServletRequest request,String keyword) {
		User user=userDao.selectByUser(Request.getUserFromHttpServletRequest(request));
		List<User> searchUserList=new ArrayList<User>();
		searchUserList=searchDao.selectUserLikeKeyword(keyword,user.getUserId());
		return searchUserList;
	}


	public List<Song> getSearchSong(String keyword) {
		List<Song> searchSongList=new ArrayList<Song>();
		searchSongList=searchDao.selectSongLikeKeyword(keyword);
		return searchSongList;
	}
	

}
