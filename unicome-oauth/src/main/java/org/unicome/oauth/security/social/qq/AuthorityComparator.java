package org.unicome.oauth.security.social.qq;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.unicome.oauth.security.domain.Authority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    public int compare(GrantedAuthority g1, GrantedAuthority g2) {
        if (g2.getAuthority() == null) {
            return -1;
        }

        if (g1.getAuthority() == null) {
            return 1;
        }

        return g1.getAuthority().compareTo(g2.getAuthority());
    }

    public static SortedSet<Authority> sortAuthorities(Collection<Authority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<Authority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());
        for (Authority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("abc123_: " + encoder.encode("abc123_"));
    }
}