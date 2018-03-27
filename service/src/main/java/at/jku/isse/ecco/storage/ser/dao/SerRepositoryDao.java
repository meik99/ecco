package at.jku.isse.ecco.storage.ser.dao;

import at.jku.isse.ecco.dao.RepositoryDao;
import at.jku.isse.ecco.repository.Repository;
import at.jku.isse.ecco.storage.mem.dao.Database;
import com.google.inject.Inject;

public class SerRepositoryDao extends SerAbstractGenericDao implements RepositoryDao {

	@Inject
	public SerRepositoryDao(SerTransactionStrategy transactionStrategy) {
		super(transactionStrategy);
	}

	@Override
	public Repository.Op load() {
		final Database root = this.transactionStrategy.getDatabase();

		return root.getRepository();
	}

	@Override
	public void store(Repository.Op repository) {
		// nothing to do
	}

}
