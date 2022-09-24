package org.dfpl.lecture.database.assignment4.assignment17011703;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	private ThreeWayBPlusTreeNode parent;
	private List<Integer> keyList;
	private List<ThreeWayBPlusTreeNode> children; //keylist보다 1개 많음

	//생성자-만들어질 때 무조건 실행되는 함수
	public ThreeWayBPlusTreeNode(ThreeWayBPlusTreeNode parent, List<Integer> keyList,List<ThreeWayBPlusTreeNode> children) {
		super();
		this.parent = parent;
		this.keyList = keyList;
		this.children = children;
	}
	

	//parent 관련
	
	public ThreeWayBPlusTreeNode getParent() {
		return parent;
	}//parent 값 보냄
	public void setParent(ThreeWayBPlusTreeNode parent) {
		this.parent = parent;
	}//parent 값 할당
	
	
	//key 값 관련
	
	public List<Integer> getKeyList() {
		return keyList;
	}//List 형태로 length를 이용해서 for문을 돌아 값을 받을 때 이용
	
	public int getKeyListSize() {
		if (this.keyList == null ) {
			return 0;
		}
		return this.keyList.size();
	}
	
	public void setKeyList(List<Integer> keyList) {
		this.keyList = keyList;
	}

	public void setKey(int e) {
		//비었을 때, 새로운 array 만들기
		if (this.keyList == null ) {
			this.keyList = new ArrayList<Integer>();
		}
		this.keyList.add(e) ;
		this.keyList.sort(null);//null이 차순 정렬
	}//key 1개를 받았을 때 삽입

	
	
	//Children 관련 
	
	public List<ThreeWayBPlusTreeNode> getChildren() {
		return children;
	}
	public int getChildrenSize() {
		if (this.children == null) {
			return 0;
		}
		return this.children.size();
	}//children 사이즈 반환
	public void setChildren(List<ThreeWayBPlusTreeNode> children) {
		this.children = children;
	}
	public void setChild(ThreeWayBPlusTreeNode node) {
		int i;
		if (this.children == null ) {
			this.children = new ArrayList<ThreeWayBPlusTreeNode>();
		}
		for (i=0;i<this.getKeyListSize();i++) {
			if (node.getKeyList().get(0) < this.keyList.get(i))
				break;
		}// 정렬해서 들어온 노드가 기존 값들과 비교해서 어디에 위치할 지 정함
		this.children.add(i,node);
	}


}
