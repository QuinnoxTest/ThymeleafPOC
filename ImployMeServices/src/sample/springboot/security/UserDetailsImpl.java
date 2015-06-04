package sample.springboot.security;


public class UserDetailsImpl /*implements UserDetailsService*/ {
	
	/*@Autowired
	CassandraOperations cassandraOperations;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		
		System.out.println("loadUserByUsernames");
		Select select = QueryBuilder.select().from("user");
		select.where(QueryBuilder.eq("username", emailId));
		
		User user = null;
		
		try
		{
			user = cassandraOperations.queryForObject(select, User.class);
		}
		catch(Exception e)
		{
			user = new User("", "", false, false, false, false, new ArrayList<GrantedAuthority>());
		}
		
		return user;
	}*/

}
