package com.min.edu.dto;

/*
 * 게시글의 전체 개수를 입력받아, 게시글의 개수와 그룹
 */
public class PagingDto {
	private int page; // 현재 페이지 번호 0부터 시작
	private int countList; // 페이지 당 몇 개(row)의 게시글을 보여줄건지
	private int totalCount; // 전체 글의 개수

	private int countPage; // 화면에 몇개의 페이지 그룹을 보여줄건지
	private int totalPage; // 우리가 원하는 한 페이지의 게시글 개수로 나누면 전체 페이지의 개수!

	private int stagePage; // 현재 그룹의 시작 페이지 번호
	private int endPage;// 현재 그룹의 마지막 페이지 번호

	public PagingDto() {
		// TODO Auto-generated constructor stub
	}

	public PagingDto(int page, int countList, int totalCount, int countPage, int totalPage, int stagePage,
			int endPage) {
		super();
		this.page = page;
		this.countList = countList;
		this.totalCount = totalCount;
		this.countPage = countPage;
		this.totalPage = totalPage;
		this.stagePage = stagePage;
		this.endPage = endPage;
	}

	public int getPage() {
		return page;
	}

	// 화면에서 입력받은 페이지의 번호가 전체 페이지의 숫자보다 크다면, 무조건 마지막 페이지로 이동!
	public void setPage(int page) {
		if (totalPage < page) {
			page = totalPage;
		}
		this.page = page;
	}

	// 페이지에 소속된 row의 개수
	public int getCountList() {
		return countList;
	}

	public void setCountList(int countList) {
		this.countList = countList;
	}

	// 화면에 보여질 페이지의 그룹
	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	// 페이지의 총 개수
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		int totalPageResult = totalCount / countList; // 몫
		if (totalCount % countList > 0) {
			totalPageResult++;
		}

		this.totalPage = totalPageResult;
	}
	// 빈- dto와 형태는 같으나 연산을 통해 새로운 값으로 만들어내는 것
	// dto- 변수 정의하고 값 이동만 하는 것
	// totalPage는 빈!

	// 그룹의 시작 번호
	public int getStagePage() {
		return stagePage;
	}

	public void setStagePage(int stagePage) {
		int stagePageResult = ((page - 1) / countPage) * countPage + 1;
		this.stagePage = stagePageResult;
	}

	// 화면에 보이는 그룹의 끝 번호
	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		int endPageResult = stagePage + countPage - 1; // 인덱스
		if (endPageResult > totalPage) {
			endPageResult = totalPage;
		}
		this.endPage = endPageResult;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	

}
