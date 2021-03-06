package org.pac4j.core.authorization.authorizer;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.profile.UserProfile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An authorizer to require elements.
 *
 * @author Jerome Leleu
 * @since 1.8.1
 */
public abstract class AbstractRequireElementAuthorizer<E extends Object> extends ProfileAuthorizer {

    protected Set<E> elements;

    @Override
    public boolean isAuthorized(final WebContext context, final SessionStore sessionStore, final List<UserProfile> profiles) {
        return isAnyAuthorized(context, sessionStore, profiles);
    }

    /**
     * Check a specific element.
     *
     * @param context the web context
     * @param sessionStore the session store
     * @param profile the profile
     * @param element the element to check
     * @return whether it is authorized for this element
     */
    protected abstract boolean check(final WebContext context, final SessionStore sessionStore,
                                     final UserProfile profile, final E element);

    public Set<E> getElements() {
        return elements;
    }

    public void setElements(final Set<E> elements) {
        this.elements = elements;
    }

    public void setElements(final List<E> elements) {
        if (elements != null) {
            this.elements = new HashSet<>(elements);
        }
    }

    public void setElements(final E... elements) {
        if (elements != null) {
            setElements(Arrays.asList(elements));
        }
    }
}
