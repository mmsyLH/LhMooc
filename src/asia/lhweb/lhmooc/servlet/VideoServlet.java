package asia.lhweb.lhmooc.servlet;

import asia.lhweb.lhmooc.annotation.WebServlet;
import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.http.LhHttpServlet;
import asia.lhweb.lhmooc.http.LhRequest;
import asia.lhweb.lhmooc.http.LhResponse;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.CourseVideo;
import asia.lhweb.lhmooc.service.CourseVideoService;
import asia.lhweb.lhmooc.service.impl.CourseVideoServiceImpl;
import asia.lhweb.lhmooc.utils.DataUtils;
import com.google.gson.Gson;

/**
 * 视频servlet
 * 视频管理 Servlet
 *
 * @author 罗汉
 * @date 2024/03/22
 */
@WebServlet()
public class VideoServlet extends LhHttpServlet {

    private CourseVideoService courseVideoService = new CourseVideoServiceImpl();
    private Gson gson = new Gson();

    @Override
    public void init() throws Exception {

    }

    public VideoServlet() {

    }

    /**
     * 分页查询课程视频记录
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void page(LhRequest req, LhResponse resp) {
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        String chapterId = req.getParameter("chapterId");

        CourseVideo courseVideo = new CourseVideo();
        if (!"".equals(chapterId)) {
            courseVideo.setChapterid(Integer.parseInt(chapterId));
        }

        Page<CourseVideo> page = courseVideoService.pageByAnd(courseVideo, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        String jsonResponse = gson.toJson(Result.success(page, "分页查询课程视频记录"));
        resp.writeToJson(jsonResponse);
    }

    /**
     * 添加视频
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void add(LhRequest req, LhResponse resp) {
        String chapterid = req.getParameter("chapterid");
        String videoname = req.getParameter("videoname");
        String videotime = req.getParameter("videotime");
        String videourl = req.getParameter("videourl");
        String userId = req.getParameter("userId");
        //判空 鉴权 判断是否存在
        if (!DataUtils.handleNullOrEmpty(resp, gson, chapterid, videoname, videotime, videourl,userId)) {
            return;
        }

        CourseVideo courseVideo = new CourseVideo();
        courseVideo.setUserid(Integer.parseInt(userId));
        courseVideo.setVideoname(videoname);
        courseVideo.setVideourl(videourl);
        courseVideo.setChapterid(Integer.parseInt(chapterid));

        Result result = courseVideoService.add(courseVideo);
        resp.writeToJson(gson.toJson(result));
    }

    /**
     * 更新视频
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void update(LhRequest req, LhResponse resp) {
        String videoid = req.getParameter("videoid");
        String chapterid = req.getParameter("chapterid");
        String videoname = req.getParameter("videoname");
        String videotime = req.getParameter("videotime");
        String videourl = req.getParameter("videourl");
        String isdelete = req.getParameter("isdelete");

        //判空
        if (!DataUtils.handleNullOrEmpty(resp, gson, videoid, chapterid, videoname, videotime, videourl, isdelete)) {
            return;
        }

        Result result = courseVideoService.update(Integer.parseInt(videoid),videoname,videourl);
        resp.writeToJson(gson.toJson(result));
    }

    /**
     * 真实删除视频
     *
     * @param req  请求对象
     * @param resp 响应对象
     */
    public void realDelete(LhRequest req, LhResponse resp) {
        String videoid = req.getParameter("videoid");

        if (!DataUtils.handleNullOrEmpty(resp, gson, videoid)) {
            return;
        }

        boolean success = courseVideoService.realDelete(Integer.parseInt(videoid));
        String jsonResponse = success ? gson.toJson(Result.success("删除成功")) : gson.toJson(Result.error("删除失败"));

        resp.writeToJson(jsonResponse);
    }

    @Override
    public void destroy() {

    }
}
