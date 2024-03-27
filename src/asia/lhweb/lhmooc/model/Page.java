package asia.lhweb.lhmooc.model;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * 分页
 *
 * @author 罗汉
 * @date 2023/04/02
 */// T表示泛型，因为将来分页模型对应的数据类型是不确定的
public class Page<T> {

	// 分页导航的字符串 有待完成。。。。。。。。
	// 因为每页显示多少条记录，是其它地方也可以使用
	// ctrl+shift+u => 切换大小写 idea
	public static final Integer PAGE_SIZE = 3;

	// 表示显示当前页[显示第几页]
	// 前端页面来的
	private Integer pageNo;
	// 表示每页显示几条记录
	private Integer pageSize = PAGE_SIZE;
	// 表示共有多少页, 他是通过totalRow和pageSize计算得到
	private Integer pageTotalCount;
	// 表示的是共有多少条记录
	// 计算得到pageTotalCount
	// 是可以同数据库DB来的->DAO
	private Integer totalRow;
	// 表示当前页，要显示的数据
	// 从DB来的->DAO
	private List<T> items;
	private String url;

	public Page() {
	}
	@NotNull
	public Page<T> getPageByList(int pageNo, int pageSize, List<T> followCourseList) {
		// 初始化分页对象，并设置分页参数及总行数
		Page<T> page = new Page<>();
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
		int startIndex = (pageNo - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, followCourseList.size());
		if (startIndex < followCourseList.size()) {
			page.setItems(followCourseList.subList(startIndex, endIndex));
		} else {
			page.setItems(Collections.emptyList()); // 如果起始索引大于等于列表大小，返回空列表
		}
		return page;
	}
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageTotalCount() {
		return pageTotalCount;
	}

	/**
	 * 设置页面总数
	 *
	 * @param pageTotalCount
	 */
	public void setPageTotalCount(Integer pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public Integer getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Page{" +
				"pageNo=" + pageNo +
				", pageSize=" + pageSize +
				", pageTotalCount=" + pageTotalCount +
				", totalRow=" + totalRow +
				", items=" + items +
				", url='" + url + '\'' +
				'}';
	}

    public boolean isEmpty() {
		return pageNo == null || pageNo == 0 || items == null || items.size() == 0;
    }
}
