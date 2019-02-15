const requiredModules = requireContext => requireContext.keys().map(requireContext);
const requiredSvgs = require.context('../../assets/svg', true, /\.svg$/);
requiredModules(requiredSvgs);