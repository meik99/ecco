package at.jku.isse.ecco.storage.xml.impl;

import at.jku.isse.ecco.core.Remote;
import at.jku.isse.ecco.dao.SettingsDao;
import at.jku.isse.ecco.storage.mem.core.MemRemote;
import at.jku.isse.ecco.storage.mem.dao.MemEntityFactory;
import com.google.inject.Inject;

import java.util.*;

import static java.util.Objects.requireNonNull;

public class XmlSettingsDao implements SettingsDao {

    private final XmlTransactionStrategy transactionStrategy;

    @Inject
    public XmlSettingsDao(XmlTransactionStrategy transactionStrategy, final MemEntityFactory entityFactory) {
        this.transactionStrategy = transactionStrategy;
    }

    @Override
    public Collection<Remote> loadAllRemotes() {
        return new ArrayList<>(transactionStrategy.load().getRemoteIndex().values());
    }

    @Override
    public Remote loadRemote(String name) {
        requireNonNull(name);
        assert !name.isEmpty() : "Expected a non-empty name!";

        return transactionStrategy.load().getRemoteIndex().get(name);
    }

    @Override
    public Remote storeRemote(Remote remote) {
        requireNonNull(remote);
        final MemRemote xmlRemote = (MemRemote) remote;
        Object returnVal = transactionStrategy.load().getRemoteIndex().putIfAbsent(xmlRemote.getName(), xmlRemote);
        assert returnVal == null;
        return xmlRemote;
    }

    @Override
    public void removeRemote(String name) {
        requireNonNull(name);
        Object returnVal = transactionStrategy.load().getRemoteIndex().remove(name);
        assert returnVal != null;
    }

    @Override
    public Map<String, String> loadPluginMap() {
        return Collections.unmodifiableMap(transactionStrategy.load().getPluginMap());
    }

    @Override
    public void addPluginMapping(String pattern, String pluginId) {
        requireNonNull(pattern);
        assert !pattern.isEmpty();
        Object returnVal = transactionStrategy.load().getPluginMap().putIfAbsent(pattern, pluginId);
        assert returnVal == null;
    }

    @Override
    public void removePluginMapping(String pattern) {
        assert pattern != null && !pattern.isEmpty();
        Object returnVal = transactionStrategy.load().getPluginMap().remove(pattern);
        assert returnVal != null;
    }

    @Override
    public Set<String> loadIgnorePatterns() {
        return Collections.unmodifiableSet(transactionStrategy.load().getIgnorePatterns());
    }

    @Override
    public void addIgnorePattern(String ignorePattern) {
        requireNonNull(ignorePattern);
        assert !ignorePattern.isEmpty();
        final boolean elementAdded = transactionStrategy.load().getIgnorePatterns().add(ignorePattern);
        assert elementAdded;
    }

    @Override
    public void removeIgnorePattern(String ignorePattern) {
        requireNonNull(ignorePattern);
        assert !ignorePattern.isEmpty();
        final boolean containedElement = transactionStrategy.load().getIgnorePatterns().remove(ignorePattern);
        assert containedElement;
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void init() {

    }
}
