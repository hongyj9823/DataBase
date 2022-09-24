package org.dfpl.lecture.database.assignment4.assignment17011703;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction�� ������ �� �����Ӱ� B+ Tree�� ���� �ȿ��� ������� ��������
	private ThreeWayBPlusTreeNode parent;
	private List<Integer> keyList;
	private List<ThreeWayBPlusTreeNode> children; //keylist���� 1�� ����

	//������-������� �� ������ ����Ǵ� �Լ�
	public ThreeWayBPlusTreeNode(ThreeWayBPlusTreeNode parent, List<Integer> keyList,List<ThreeWayBPlusTreeNode> children) {
		super();
		this.parent = parent;
		this.keyList = keyList;
		this.children = children;
	}
	

	//parent ����
	
	public ThreeWayBPlusTreeNode getParent() {
		return parent;
	}//parent �� ����
	public void setParent(ThreeWayBPlusTreeNode parent) {
		this.parent = parent;
	}//parent �� �Ҵ�
	
	
	//key �� ����
	
	public List<Integer> getKeyList() {
		return keyList;
	}//List ���·� length�� �̿��ؼ� for���� ���� ���� ���� �� �̿�
	
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
		//����� ��, ���ο� array �����
		if (this.keyList == null ) {
			this.keyList = new ArrayList<Integer>();
		}
		this.keyList.add(e) ;
		this.keyList.sort(null);//null�� ���� ����
	}//key 1���� �޾��� �� ����

	
	
	//Children ���� 
	
	public List<ThreeWayBPlusTreeNode> getChildren() {
		return children;
	}
	public int getChildrenSize() {
		if (this.children == null) {
			return 0;
		}
		return this.children.size();
	}//children ������ ��ȯ
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
		}// �����ؼ� ���� ��尡 ���� ����� ���ؼ� ��� ��ġ�� �� ����
		this.children.add(i,node);
	}


}
