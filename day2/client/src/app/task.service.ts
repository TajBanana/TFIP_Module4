import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {lastValueFrom} from "rxjs";
import {Task} from "./models";

@Injectable()
export class TaskService {
	constructor(private http: HttpClient) { }

	getAllTasks(): Promise<Task[]> {
		return lastValueFrom(this.http.get<Task[]>('http://localhost:8080/api/tasks'))
	}

	postTask(task: Task): Promise<void> {
		return lastValueFrom(this.http.post<void>('http://localhost:8080/api/task', task))
	}
}
