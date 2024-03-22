package asia.lhweb.lhmooc.service.impl;

import asia.lhweb.lhmooc.common.Result;
import asia.lhweb.lhmooc.dao.FollowCourseDAO;
import asia.lhweb.lhmooc.dao.impl.FollowCourseDAOImpl;
import asia.lhweb.lhmooc.model.Page;
import asia.lhweb.lhmooc.model.bean.FollowCourse;
import asia.lhweb.lhmooc.service.FollowCourseService;

import java.util.List;

/**
 * @author :罗汉
 * @date : 2024/3/13
 */
public class FollowCourseServiceImpl implements FollowCourseService {
    private FollowCourseDAO followCourseDAO = new FollowCourseDAOImpl();

    /**
     * 通过用户id获取关注页面，查询后按照收藏时间降序分页展示。
     * <p>
     * 该方法首先查询出指定用户收藏的所有课程，然后对这些课程按照收藏时间进行降序排序。
     * 排序后的结果将进行分页处理，返回指定页码和页面大小的收藏课程列表。
     *
     * @param userId   用户的唯一标识符，用于查询该用户收藏的课程。
     * @param pageNo   请求的页码，表示需要返回的页码。
     * @param pageSize 页面大小，表示每页返回的收藏课程数量。
     * @return 返回一个包含收藏课程分页信息的结果对象。如果查询失败或数据为空，返回错误信息；否则返回查询成功的收藏课程分页列表。
     */
    @Override
    public Result<Page<FollowCourse>> getFollowPageById(int userId, int pageNo, int pageSize) {
        // 根据用户id查询该用户收藏的所有课程
        FollowCourse followCourse = new FollowCourse();
        followCourse.setUserid(userId);
        List<FollowCourse> followCourseList = followCourseDAO.selectAll(followCourse);
        // 如果查询结果为空，返回查询失败的错误信息
        if (followCourseList.isEmpty()) {
            return Result.error("查询失败，数据为空");
        }
        // 对查询结果按照收藏时间进行降序排序
        followCourseList.sort((o1, o2) -> o2.getCreatetime().compareTo(o1.getCreatetime()));

        // 初始化分页对象，并设置分页参数及总行数
        Page<FollowCourse> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRow(followCourseList.size());

        // 计算总页数
        int pageTotalCount = followCourseList.size() / pageSize;
        if (followCourseList.size() % pageSize > 0) {
            pageTotalCount = pageTotalCount + 1;
        }
        page.setPageTotalCount(pageTotalCount);

        // 设置分页数据，取出指定页的收藏课程列表
        page.setItems(followCourseList.subList((pageNo - 1) * pageSize, pageNo * pageSize));

        // 返回查询成功的结果，包含分页的收藏课程列表
        return Result.success(page, "查询成功");
    }

    /**
     * 页面
     *
     * @param followCourse 遵循课程
     * @param pageNo       页面没有
     * @param pageSize     页面大小
     * @return {@link Page}<{@link FollowCourse}>
     */
    @Override
    public Page<FollowCourse> page(FollowCourse followCourse, int pageNo, int pageSize) {
        return followCourseDAO.page(followCourse,pageNo,pageSize);
    }

    /**
     * 真正删除
     *
     * @param followCourse 遵循课程
     * @return boolean
     */
    @Override
    public boolean realDelete(FollowCourse followCourse) {
        return followCourseDAO.realDelete(followCourse)!=-1;
    }

}
