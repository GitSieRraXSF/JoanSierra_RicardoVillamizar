package data;

import java.util.ArrayList;

public interface CRUD_Operation<S, T> {
	//CRUD:
	void save(S entity);
	ArrayList<S> fetch();
	void update(S entity);
	void delete(T referencia);
	boolean authenticate(T referencia);
	boolean authenticate1(String nickname);
}
