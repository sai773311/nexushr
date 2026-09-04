# NexusHR frontend

React + Vite client for the existing NexusHR Spring Boot API.

## Run the complete project

1. Start MySQL and create the database configured in `backend/src/main/resources/application.yml`:

   ```sql
   CREATE DATABASE nexushr;
   ```

2. From `nexushr`, start the backend:

   ```powershell
   mvn spring-boot:run -pl backend -am
   ```

3. In a second terminal, start the frontend:

   ```powershell
   cd frontend
   npm install
   npm run dev
   ```

4. Open http://localhost:5173.

The Vite proxy forwards `/api` requests to `http://localhost:8080`. The frontend stores the access JWT and refresh token returned by `/api/auth/login` and sends the access token as a Bearer token on protected requests. Recruitment APIs accept `HR`, `RECRUITER`, and `ADMIN` roles.

## Included workflows

- People directory backed by `/api/employees`
- Departments and designations backed by their CRUD APIs
- Dashboard metrics backed by `/api/dashboard`
- Employee attendance check-in and attendance table
- Leave request form and leave desk table
- Payroll, recruitment, performance, documents, notifications, AI insights, reports, and admin data views backed by their existing APIs
- HR, manager, training, assets, ESS, audit, and workflow records backed by `/api/workspace/{module}`

## Workspace API

The modules without existing typed domain entities use a persistent record API:

- `GET /api/workspace/{module}` lists records
- `POST /api/workspace/{module}` creates a record
- `PUT /api/workspace/{module}/{id}` updates a record
- `DELETE /api/workspace/{module}/{id}` deletes a record

Records are stored in the MySQL `workspace_records` table. Use the `feature` field to distinguish sub-features such as `GOALS`, `KPI_TRACKING`, `LAPTOP_ALLOCATION`, `AUDIT_LOGS`, or `LEAVE_WORKFLOW`.

## Backend coverage

The current Spring service has typed APIs for core HR, attendance, leave, payroll, recruitment, performance, documents, notifications, reports, and AI insights. The new workspace API supplies real MySQL CRUD storage for HR, manager, training, assets, ESS, audit, and workflow records. External delivery integrations such as SMS, push notifications, and model-backed AI analysis still require provider credentials and implementation-specific services; the UI stores their operational records without fabricating provider results.

The database connection currently follows the credentials in `application.yml` (`root` / `root`). Change that file before starting the API if your local MySQL credentials differ.