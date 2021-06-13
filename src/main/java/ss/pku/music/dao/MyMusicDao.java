package ss.pku.music.dao;

import java.util.List;

import ss.pku.music.model.Song;
import ss.pku.music.model.User;

public interface MyMusicDao {

	/**
	 * 查询当前用户收藏的歌曲信息列表
	 * @param user
	 * @return
	 */
	public List<Song> selectCollectedSong(User user);

	/**
	 * 查询当前用户最近的播放列表
	 * @param user
	 * @return
	 */
	public List<Song> selectMyRecentSong(User user);
	
}
