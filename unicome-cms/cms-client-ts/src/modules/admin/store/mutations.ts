export const mutations = {
    ['SET_API_CALL_ERROR'](state: any, url: string) {
        state.apiCall.push("error");
    },
    ['CLEAR_API_CALL_ERROR'](state: any, url: string) {
        state.apiCall = [];
    }
}