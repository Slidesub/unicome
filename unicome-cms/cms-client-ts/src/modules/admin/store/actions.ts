import { Commit } from 'vuex';
import Vue from 'vue';

export const handleError = (context: { commit: Commit }, error: any) => {
    if (error.response) {
        console.log(error.response.data);
        console.log(error.response.status);
        context.commit('SET_API_CALL_ERROR');
    } else if (error.request) {
        if (error.code === 'ECONNABORTED' &&
            error.message.startWith('timeout') &&
            error.message.endWith('exceeded')) {
            console.log(error.message);
        } else {
            console.log(error.request);
        }
    } else {
        console.log(error.message);
    }
}

export const fetchArticles = async function(context: { commit: Commit }): Promise<any> {
    Vue.$http.get('/api/articles/').then(resp => {
        context.commit('CLEAR_API_CALL');
        return resp.data;
    }).catch(err => {
       handleError(context, err);
    });
}


