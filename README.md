## Spring Boot - School

### Quick Setup

#### Run
```bash
docker compose up -d
```

#### Stop
> This will remove all volumes and images. Remove the flag --volumes to keep the db data
```bash
docker compose down --volumes --rmi local
```

### Must Read
- Don't forget to change the password in `db/password.txt` to something else and move it to a secure location 
- Check application.properties and change the settings accordingly, db is on create-drop mode by default
- Default Spring Boot port is `8080` and the db port is `5432` (remove the ports setting on the yaml when not needed) )
