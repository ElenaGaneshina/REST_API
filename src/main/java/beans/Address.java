
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Address {

    @SerializedName("country_code")
    @Expose
    public String countryCode;
    @SerializedName("postal_code")
    @Expose
    public String postalCode;
    @SerializedName("formatted")
    @Expose
    public String formatted;
    @SerializedName("Components")
    @Expose
    public List<Component> components = new ArrayList<Component>();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(countryCode).append(postalCode).append(formatted).append(components).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Address) == false) {
            return false;
        }
        Address rhs = ((Address) other);
        return new EqualsBuilder().append(countryCode, rhs.countryCode).append(postalCode, rhs.postalCode).append(formatted, rhs.formatted).append(components, rhs.components).isEquals();
    }

}
