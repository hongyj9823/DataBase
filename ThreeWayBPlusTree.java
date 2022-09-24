package org.dfpl.lecture.database.assignment4.assignment17011703;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.SortedSet;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class ThreeWayBPlusTree implements NavigableSet<Integer> {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
		private ThreeWayBPlusTreeNode root;
		private LinkedList<ThreeWayBPlusTreeNode> leafList;
		//1~17까지 이어져있는 실질적인 모든 value값을 가지고 있는 것이 leafList(마지막)
		//출력 모든 값에 접근하기 위해
		/**
		 * 과제 Assignment4를 위한 메소드:
		 * 
		 * key로 검색하면 root부터 시작하여, key를 포함할 수 있는 leaf node를 찾고 key가 실제로 존재하면 해당 Node를
		 * 반환하고, 그렇지 않다면 null을 반환한다. 중간과정을 System.out.println(String) 으로 출력해야 함. 3 way
		 * B+ tree에서 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17 이 순서대로
		 * add되었다고 했을 때,
		 * 
		 * 예: getNode(15)을 수행하였을 때 
		 * > start finding 15
		 * > larger than or equal to 9
		 * > larger than or equal to 13
		 * > larger than or equal to 15
		 * > less than 16
		 * > 15 found 
		 * 위의 6 문장을
		 * 콘솔에 출력하고 15를 포함한 ThreeWayBPlusTreeNode를 반환함
		 * 
		 * @param key
		 * @return
		 */
		public ThreeWayBPlusTreeNode getNode(Integer key) {
			// TODO Auto-generated method stub
			//childNode가 NULL이 아닐때까지 돌기
			ThreeWayBPlusTreeNode temp=root;
			ThreeWayBPlusTreeNode result=null;// 결과값 반환
			int i=0;
			System.out.println("start finding "+key);
			while(temp.getChildren()!=null)// leafnode 만나면 끝
			{
				for( i=0;i<temp.getKeyListSize();i++)//해당 keylist와 모두 비교해야함
				{
					if(key<temp.getKeyList().get(i))
					{
						System.out.println("less than "+temp.getKeyList().get(i));
						temp=temp.getChildren().get(i);// 작으면 왼쪽으로 이동
						break;
					}
				}
				if(i==temp.getKeyListSize())//같거나 큰 경우
				{
					System.out.println("larger than or equal to "+temp.getKeyList().get(i-1));
					temp=temp.getChildren().get(i);
				}
			}
			   if(key<temp.getKeyList().get(0))//하단 값에서 가장 작은 값인 0부터 비교 시작//들어있는 값보다 작을 시, 출력
		        {
		           System.out.println("less than "+temp.getKeyList().get(i));
		           System.out.println(key+ " not found");
		           result=null;//반환해주기 위해 키를 못찾았을 경우 result값에 null를 넣어줌		          	           
		        }
		        else if(key>temp.getKeyList().get(0))//클 시 출력
		        {//다음의 수보다 클수 있기 때문에 for 문을 추가해줌
		           for( i=temp.getKeyListSize()-1;i>0;i--)//마지막 keylist와 모두 비교해야함// 18이 들어왔을 경우를 예시로 생각(17보다 큼을 출력해야함)
		           {
		              if(key>temp.getKeyList().get(i)) {
		                 System.out.println("larger than or equal to "+temp.getKeyList().get(i));
		                 System.out.println(key+ " not found");
		                 result=null;//반환해주기 위해 키를 못찾았을 경우 result값에 null를 넣어줌
		                 }
		           }
		        }
		        else
		        {
		           System.out.println(key+ " found");//같을 시 출력
		           result=temp; //반환해주기 위해 키를 찾았을 경우 result값에 temp를 넣어줌
		        }
			   return result;
}
		
		/**
		 * 과제 Assignment4를 위한 메소드: 
		 * 
		 * inorder traversal을 수행하여, 값을 오름차순으로 출력한다. 
		 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17 이 순서대로
		 * add되었다고 했을 때,
		 * 1
		 * 2
		 * 3
		 * 4
		 * 5
		 * 6
		 * 7
		 * 8
		 * 9
		 * 10
		 * 11
		 * 12
		 * 13
		 * 14
		 * 15
		 * 16
		 * 17
		 * 위와 같이 출력되어야 함. 
		 * Note: Bottom의 LinkedList 순회를 하면 안됨
		 */
		
		
	public void inorderTraverse() {
		// TODO Auto-generated method stub
		//root를 temp로 받아서 안에서 전위 순회
		inorder(root);

	}

	
	public void inorder(ThreeWayBPlusTreeNode node)
	{
		if(node!=null)
		{			
			if(node.getChildren()!=null){inorder(node.getChildren().get(0));}//왼쪽으로 먼저 내려감
			for(int i=0;i<node.getKeyListSize();i++)//해당 keylist에 있는 값을 출력
			{
			System.out.println(node.getKeyList().get(i)+"\n");
			}
			if(node.getChildren()!=null){inorder(node.getChildren().get(1));}//오른쪽
		}
	}

	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer first() {
		// TODO Auto-generated method stub
		ThreeWayBPlusTreeNode temp = root;
		while(temp.getChildren()!=null) { 
			temp = temp.getChildren().get(0) ; //왼쪽에 부모보다 작은 자식이 위치
		}
		return temp.getKeyList().get(0); 
	}
//제일 작은 값 반환
	@Override
	public Integer last() {
		// TODO Auto-generated method stub
		ThreeWayBPlusTreeNode temp = root;
		while(temp.getChildren()!=null) {  
			temp = temp.getChildren().get(temp.getChildrenSize()-1); //오른쪽이 부모보다 큰 자식이 위치
		}
		return temp.getKeyList().get(temp.getKeyListSize()-1); 
	}
//제일 큰 값 반환
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
			return root==null;
	}
	@Override
	public boolean add(Integer e) {
		// TODO Auto-generated method stub
		//insert 함수 1-17까지 넣기
		//tree가 비었는지 확인 isEmpty() 
	 if (isEmpty()){//비었는지 확인
		 root=new ThreeWayBPlusTreeNode(null,null,null);//parent,keyList,Child
		 root.setKey(e);	//새로운 root를 생성해서 키 값을 넣어줌
	 }
	 else{//안비었으면,,
		ThreeWayBPlusTreeNode temp=root;//temp를 통해서 root부터 leafnode(temp.child==Null)를 찾을 떄까지 내려감
		int i=0;		
		while(temp.getChildren()!=null)// 삽입될 위치 먼저 찾기 위해 leaf node 찾기
		{
			for( i=0;i<temp.getKeyListSize();i++)//
			{
				if(e<temp.getKeyList().get(i))
				{		
					break;
				}
			}
			temp=temp.getChildren().get(i);
			 //부모가 0일때 조건을 만족하면 자식도 0으로 내려감,,
		}
		//삽입 시작
		temp.setKey(e);//정렬이 알아서 됨

		//위배되었는지 확인 시작	
		if(temp.getKeyListSize()>=3)//max조건을 위배,사이즈가 3이 되었을 때
		{
			if(temp.getParent()==null){//temp가 root일 때//첫위배
				ThreeWayBPlusTreeNode newnodeA =new ThreeWayBPlusTreeNode(null,null,null);
				ThreeWayBPlusTreeNode newnodeB =new ThreeWayBPlusTreeNode(null,null,null);
				newnodeA.setKey(temp.getKeyList().get(0));//keylist의 0번째의 값
				newnodeB.setKey(temp.getKeyList().get(1));//root에 넣어줄 값을 넣어줌//newnodeB 가 root//keylist의 1번째의 값(중간 값)
				newnodeA.setParent(newnodeB);//루트값을 부모로 둠
				temp.setParent(newnodeB);//newnodeB==root
				temp.getKeyList().remove(0);// 값을 지우고
				newnodeB.setChild(newnodeA);//루트이기 때문에 Child로 newnodeA와 temp를 둠
				newnodeB.setChild(temp);
				root=newnodeB;//root에  newnodeB의 값을 넣는다
			}
			else {//temp가 root가 아닐때,,				
				temp.getParent().setKey(temp.getKeyList().get(1));// Parent에 현재 temp의 중간 값을 삽입
				//1. new node 만들기
				ThreeWayBPlusTreeNode newnode =new ThreeWayBPlusTreeNode(null,null,null);
				//2. new node에 temp 값 인덱스 1,2 넣기
				newnode.setKey(temp.getKeyList().get(1));
				newnode.setKey(temp.getKeyList().get(2));
				//3. temp의 keylist 값 인덱스 1,2 지우기
				temp.getKeyList().remove(1);
				temp.getKeyList().remove(1);//자리가 밀리기때문에 같은 위치의 값으로 지워줘야 오류가 안남
				//4. new node의 parent 값 설정
				newnode.setParent(temp.getParent());
				//5. parent의 child를 new node로 설정
				temp.getParent().setChild(newnode);// setChild로 정렬가능
			}
		}
		temp=temp.getParent();// temp위치보다 한단계 위로 올라가서 parent가 값을 위배하는지 확인할 것
		while(temp!=null)//root가 될때까지 temp를 temp의 parent 값으로 갱신시켜 max 조건이 위배되는 것이 없는지 확인
		{
			if(temp.getKeyListSize()<3)//현재의 temp가 사이즈 max 조건이 위배되지 않는다면 반복문을 탈출
			{
				break;
			}
			if(temp.getParent()==null)//temp가 root일 때,,
			{
				root=new ThreeWayBPlusTreeNode(null,null,null);
				temp.setParent(root);
				root.setChild(temp);//새로운 root를 생성
			}
			////split/////
			temp.getParent().setKey(temp.getKeyList().get(1));//parent에 temp의 가운데 keylist 값을 parent에 삽입
			//temp의 0번째,2번째 split
			ThreeWayBPlusTreeNode newnode2 =new ThreeWayBPlusTreeNode(null,null,null);
			newnode2.setKey(temp.getKeyList().get(2));
			temp.getChildren().get(2).setParent(newnode2);
			temp.getChildren().get(3).setParent(newnode2);//temp의 Child노드들에게 새로운 노드(newnode2)를 부모로 두게 해야함//아니라면 이전의 부모가 유지되어 트리의 형태가 망가짐
			
			newnode2.setChild(temp.getChildren().get(2));//새로운 노드에게 temp의 Child를 Child로 두게 함
			newnode2.setChild(temp.getChildren().get(3));
			
			temp.getChildren().remove(2);
			temp.getChildren().remove(2);
			temp.getKeyList().remove(1);
			temp.getKeyList().remove(1);//temp가 가지고 있던 기존 값들은 지워줌
			
			temp.getParent().setChild(newnode2);//temp의 부모가 새로운 노드를 Child로 두게 함
			newnode2.setParent(temp.getParent());

			temp=temp.getParent();//parent로 temp 이동
			
			}
	 }	
	 return true;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer lower(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer floor(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer ceiling(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer higher(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> descendingSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
			boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> headSet(Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> tailSet(Integer fromElement) {
		// TODO Auto-generated method stub
		return null;
	}

}
