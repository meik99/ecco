package at.jku.isse.ecco.feature;

import at.jku.isse.ecco.dao.Persistable;

/**
 * Represents a version of a feature.
 */
public interface FeatureRevision extends Persistable {

	/**
	 * Returns the feature belonging to this version.
	 *
	 * @return The feature belonging to this version.
	 */
	public Feature getFeature();

	public String getId();

	public String getDescription();

	public void setDescription(String description);


	@Override
	public int hashCode();

	@Override
	public boolean equals(Object object);

}
