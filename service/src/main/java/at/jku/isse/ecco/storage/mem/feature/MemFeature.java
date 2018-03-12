package at.jku.isse.ecco.storage.mem.feature;

import at.jku.isse.ecco.feature.Feature;
import at.jku.isse.ecco.feature.FeatureRevision;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Memory implementation of {@link Feature}.
 */
public class MemFeature implements Feature {

	private String id;
	private String name;
	private String description;
	private List<FeatureRevision> revisions;


	public MemFeature(String id, String name) {
		checkNotNull(id);
		checkNotNull(name);
		this.id = id;
		this.name = name;
		this.description = "";
		this.revisions = new ArrayList<>();
	}


	@Override
	public Collection<FeatureRevision> getRevisions() {
		return Collections.unmodifiableCollection(this.revisions);
	}

	@Override
	public FeatureRevision addRevision(String id) {
		MemFeatureRevision featureVersion = new MemFeatureRevision(this, id);
		if (!this.revisions.contains(featureVersion)) {
			this.revisions.add(featureVersion);
//			if (this.nextVersion <= version)
//				this.nextVersion = version + 1;
			return featureVersion;
		}
		return null;
	}

	@Override
	public FeatureRevision getRevision(String id) {
		for (FeatureRevision featureVersion : this.revisions) {
			if (featureVersion.getId().equals(id))
				return featureVersion;
		}
		return null;
	}

	@Override
	public FeatureRevision getLatestRevision() {
		if (this.revisions.isEmpty())
			return null;
		return this.revisions.get(this.revisions.size() - 1);
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		checkNotNull(name);
		checkArgument(!name.isEmpty(), "Expected a non-empty name but was empty.");

		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(final String description) {
		checkNotNull(description);

		this.description = description;
	}


	@Override
	public boolean equals(final Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof MemFeature)) return false;

		final Feature other = (Feature) obj;
		return this.id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name);
	}

	@Override
	public String toString() {
		return this.getFeatureString();
	}

}
