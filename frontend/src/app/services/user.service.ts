import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import {HttpClient} from '@angular/common/http';
import {UserList} from '../interfaces/user-list.interface';
import {UserAccountModel} from '../pages/manage-users/user/user-account.model';
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private api: string = environment.apiUrl + '/user';

    constructor(private http: HttpClient) {
    }

    findAll(): Observable<UserList[]> {
        return this.http.get<UserList[]>(this.api + '/');
    }

    findOne(id: number): Observable<UserAccountModel> {
        return this.http.get<UserAccountModel>(this.api + '/' + id);
    }

    validateUsername(id: number, username: string): Observable<boolean> {
        const queryParams = `?id=${id}&username=${username}`;
        return this.http.get<boolean>(this.api + '/validate-username' + queryParams);
    }

    save(user: UserAccountModel): Observable<UserAccountModel> {
        return this.http.post<UserAccountModel>(this.api + '/', user);
    }

    saveProfile(user: UserAccountModel, file: File): Observable<UserAccountModel> {
        const formData = new FormData();
        if (file) {
            formData.append('file', file, file.name);
        }
        formData.append('model', JSON.stringify(user));

        return this.http.post<UserAccountModel>(this.api + '/save-profile', formData);
    }

    remove(user: UserAccountModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + user.id);
    }

}
