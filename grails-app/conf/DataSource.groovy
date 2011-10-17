dataSource {
    pooled = true    
    driverClassName = "org.postgresql.Driver"
    username = "postgres"    
    password = "admin"
    dbCreate = "update" // create, create-drop, update    
    url = 'jdbc:postgresql:clase'
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
        }
    }
    test {
        dataSource {
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql:puntoventa2"
        }
        hibernate {
            show_sql = false
        }
    }
}
