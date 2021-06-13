package ss.pku.music.controller;

import ss.pku.music.service.impl.RecordPlayServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class RecordPlayControllerTest extends TestCase {
   @Autowired
   private RecordPlayServiceImpl recordPlayService;

   @RequestMapping("recordPlay.do")
   @Test
    public void testRecordPlay() throws Exception{

       //HttpServletRequest request=;

       //RecordPlayDao recordPlayDao=recordPlayService.recordPlay(request,3);

    }
}